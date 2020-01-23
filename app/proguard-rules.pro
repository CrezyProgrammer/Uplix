

-keep class * extends com.stfalcon.chatkit.messages.MessageHolders$OutcomingTextMessageViewHolder {
     public <init>(android.view.View, java.lang.Object);
     public <init>(android.view.View);
 }
  -keepclassmembers class com.up.lix.** {
       *;
     }

-keep class * extends com.stfalcon.chatkit.messages.MessageHolders$IncomingTextMessageViewHolder {
     public <init>(android.view.View, java.lang.Object);
     public <init>(android.view.View);
 }
-keep class * extends com.stfalcon.chatkit.messages.MessageHolders$IncomingImageMessageViewHolder {
     public <init>(android.view.View, java.lang.Object);
     public <init>(android.view.View);
 }
-keepclassmembers class * {*;}
-keep class * extends com.stfalcon.chatkit.messages.MessageHolders$OutcomingImageMessageViewHolder {
     public <init>(android.view.View, java.lang.Object);
     public <init>(android.view.View);
 }
