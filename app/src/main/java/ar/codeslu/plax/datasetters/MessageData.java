package ar.codeslu.plax.datasetters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ar.codeslu.plax.global.Global;

import com.stfalcon.chatkit.me.Message;
import com.stfalcon.chatkit.me.MessageIn;
import com.stfalcon.chatkit.me.UserIn;

import se.simbio.encryption.Encryption;

import static ar.codeslu.plax.datasetters.DialogData.timeStamp;

/**
 * Created by mostafa on 30/01/19.
 */


public class MessageData {

    static ArrayList<MessageIn> messagelist;
    static ArrayList<Message> chats;
    DatabaseReference mData;
    static Encryption encryption;


    private MessageData() {
        throw new AssertionError();
    }


    public static Message getImageMessage(String link, String id, int i, Date creat, String type, boolean deleted) {
        Message message = new Message(id, getUser(messagelist.get(i).getFrom()), link, creat, messagelist.get(i).getStatue(), type, messagelist.get(i).getMessId(), messagelist.get(i).isDeleted(),messagelist.get(i).getReact());
        message.setImage(new Message.Image(link));
        return message;
    }

    public static Message getVoiceMessage(String link, String duration, String id, int i, Date creat, String type, boolean deleted) {
        Message message = new Message(id, getUser(messagelist.get(i).getFrom()), link, creat, messagelist.get(i).getStatue(), type, messagelist.get(i).getMessId(), messagelist.get(i).isDeleted(),messagelist.get(i).getReact());
        message.setVoice(new Message.Voice(link, duration));
        return message;
    }

    public static Message getFileMessage(String link, String filename, String id, int i, Date creat, String type, boolean deleted) {
        Message message = new Message(id, getUser(messagelist.get(i).getFrom()), link, creat, messagelist.get(i).getStatue(), type, messagelist.get(i).getMessId(), messagelist.get(i).isDeleted(),messagelist.get(i).getReact());
        message.setFile(new Message.File(link, filename));
        return message;
    }

    public static Message getVideoMessage(String link, String duration, String id, int i, Date creat, String type, String thumb, boolean deleted) {
        Message message = new Message(id, getUser(messagelist.get(i).getFrom()), link, creat, messagelist.get(i).getStatue(), type, messagelist.get(i).getMessId(), thumb, messagelist.get(i).isDeleted(),messagelist.get(i).getReact());
        message.setVideo(new Message.Video(link, duration, thumb));
        return message;
    }

    public static Message getTextMessage(String text, String id, int i, Date creat, String type, boolean deleted) {
        return new Message(id, getUser(messagelist.get(i).getFrom()), text, creat, messagelist.get(i).getStatue(), type, messagelist.get(i).getMessId(), messagelist.get(i).isDeleted(),messagelist.get(i).getReact());
    }

    public static Message getLocationMessage(String location, String id, int i, Date creat, String type, boolean deleted) {
        Message message = new Message(id, getUser(messagelist.get(i).getFrom()), location, creat, messagelist.get(i).getStatue(), type, messagelist.get(i).getMessId(), messagelist.get(i).isDeleted(),messagelist.get(i).getReact());
        message.setMap(new Message.Map(location));
        return message;
    }

    public static ArrayList<Message> getMessages() {
        messagelist = new ArrayList<>();
        messagelist.clear();
        messagelist = Global.messG;
        byte[] iv = new byte[16];
        encryption = Encryption.getDefault(Global.keyE, Global.salt, iv);
        ArrayList<Message> chats = new ArrayList<>();
        if (chats.size() != 0)
            chats.clear();
        for (int i = 0; i < messagelist.size(); i++) {
            Calendar calendar = Calendar.getInstance();
            timeStamp = messagelist.get(i).getTime();
            calendar.setTimeInMillis(timeStamp);
            if (messagelist.get(i).getType().equals("text")) {
                chats.add(getTextMessage(encryption.decryptOrNull(messagelist.get(i).getMessage()), messagelist.get(i).getFrom(), i, calendar.getTime(), messagelist.get(i).getType(), messagelist.get(i).isDeleted()));
            } else if (messagelist.get(i).getType().equals("image")) {
                chats.add(getImageMessage(encryption.decryptOrNull(messagelist.get(i).getLinkI()), messagelist.get(i).getFrom(), i, calendar.getTime(), messagelist.get(i).getType(), messagelist.get(i).isDeleted()));
            } else if (messagelist.get(i).getType().equals("voice")) {
                chats.add(getVoiceMessage(encryption.decryptOrNull(messagelist.get(i).getLinkV()), messagelist.get(i).getDuration(), messagelist.get(i).getFrom(), i, calendar.getTime(), messagelist.get(i).getType(), messagelist.get(i).isDeleted()));
            } else if (messagelist.get(i).getType().equals("file")) {
                chats.add(getFileMessage(encryption.decryptOrNull(messagelist.get(i).getLinkF()), messagelist.get(i).getFilename(), messagelist.get(i).getFrom(), i, calendar.getTime(), messagelist.get(i).getType(), messagelist.get(i).isDeleted()));
            } else if (messagelist.get(i).getType().equals("video")) {
                chats.add(getVideoMessage(encryption.decryptOrNull(messagelist.get(i).getLinkVideo()), messagelist.get(i).getDuration(), messagelist.get(i).getFrom(), i, calendar.getTime(), messagelist.get(i).getType(), messagelist.get(i).getThumb(), messagelist.get(i).isDeleted()));
            } else if (messagelist.get(i).getType().equals("map")) {
                chats.add(getLocationMessage(encryption.decryptOrNull(messagelist.get(i).getLocation()), messagelist.get(i).getFrom(), i, calendar.getTime(), messagelist.get(i).getType(), messagelist.get(i).isDeleted()));
            }
        }
        return chats;
    }

    private static UserIn getUser(String id) {

        if (id.equals(FirebaseAuth.getInstance().getUid())) {
            return new UserIn(
                    id, Global.myname, Global.myava);
        } else {
            return new UserIn(
                    id, Global.currname, Global.currAva);

        }


    }
}