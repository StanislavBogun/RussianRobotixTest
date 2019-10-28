import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Main {
        public static void main(String args[]) throws MessagingException, IOException, SQLException, ClassNotFoundException, ParseException {
            receiveMessage();

            CSVDriver csv = new CSVDriver();
            String vendor = "Запчасти даром";
            List<Report> reportList = csv.getReportList(vendor);
            DBDriver db = DBDriver.getInstance();
            db.Conn();
            db.WriteDB(reportList, vendor);
        }

        static void receiveMessage() throws MessagingException, IOException, ParseException {
            JSONObject conf = Config.getEmailConf();

            String in_user = (String) conf.get("login");
            String in_password = (String) conf.get("password");
            Authenticator auth = new CAuthenticator(in_user, in_password);

            Properties properties = new Properties();
            properties.put("mail.imap.auth", conf.get("mail.imap.auth"));
            properties.put("mail.imap.socketFactory.port", conf.get("mail.imap.socketFactory.port"));
            properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("imap");

            store.connect((String) conf.get("host"),  in_user, in_password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            int count = inbox.getMessageCount();
            Message[] messages =  inbox.getMessages(1, count);

            ArrayList<String> attachments = new ArrayList<>();

            LinkedList<MessageBean> listMessages = getPart(messages, attachments);

            inbox.setFlags(messages, new Flags(Flags.Flag.SEEN), true);
            inbox.close(false);
            store.close();
        }

        private static LinkedList<MessageBean> getPart(Message[] messages, ArrayList<String> attachments) throws MessagingException, IOException {
            LinkedList<MessageBean> listMessages = new LinkedList<>();
            SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            for (Message inMessage : messages) {
                attachments.clear();
                if (inMessage.isMimeType("text/plain")) {
                    MessageBean message = new MessageBean(inMessage.getMessageNumber(), MimeUtility.decodeText(inMessage.getSubject()), inMessage.getFrom()[0].toString(), null, f.format(inMessage.getSentDate()), (String) inMessage.getContent(), false, null);
                    listMessages.add(message);
                } else if (inMessage.isMimeType("multipart/*")) {
                    Multipart mp = (Multipart) inMessage.getContent();
                    MessageBean message = null;
                    for (int i = 0; i < mp.getCount(); i++) {
                        Part part = mp.getBodyPart(i);
                        if ((part.getFileName() == null || part.getFileName().equals("")) && part.isMimeType("text/plain")) {
                            message = new MessageBean(inMessage.getMessageNumber(), inMessage.getSubject(), inMessage.getFrom()[0].toString(), null, f.format(inMessage.getSentDate()), (String) part.getContent(), false, null);
                        } else {
                            if (part.getFileName() == null) {
                                part.getFileName();
                            }
                            if ((part.getDisposition() != null) && (part.getDisposition().equals(Part.ATTACHMENT))) {
                                attachments.add(saveFile(MimeUtility.decodeText(part.getFileName()), part.getInputStream()));
                                if (message != null) message.setAttachments(attachments);
                            }
                        }
                    }
                    listMessages.add(message);
                }
            }
            return listMessages;
        }

        //метод определения расширения файла
        private static String getFileExtension(String fileName) {
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
                return fileName.substring(fileName.lastIndexOf(".")+1);
            else return "";
        }

        private static String saveFile(String filename, InputStream input) {
            String path = "C:\\Users\\koytt\\IdeaProjects\\Robotics\\src\\main\\resources\\files\\"+filename;
            try {
                byte[] attachment = new byte[input.available()];
                input.read(attachment);
                // Сохраняем только CSV файлы
                if(getFileExtension(filename).equals("csv")) {
                    File file = new File(path);
                    FileOutputStream out = new FileOutputStream(file);
                    out.write(attachment);
                    input.close();
                    out.close();
                }
                return path;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        }
}
