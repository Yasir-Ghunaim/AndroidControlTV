<h2 dir="RTL">تعليمات الإستخدام</h3>
<h3 dir="RTL">أندرويد:</h3>
<p dir="RTL"> في﻿ ملف Main.java الخاص بالأندرويد قم بتعديل الآتي:</p>
 
<p dir="RTL">
اذهب إلى سطر 36 أو ابحث عن "UUID MY_UUID" وقم بوضع رقم UUID الخاص بجوالك وللحصول عليه قم بتنفيذ الأوامر التالية:
</p>
<p>
TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
<br>
String uuid = tManager.getDeviceId();
<br>
Log.d("UUID", "My UUID is: " + uuid);
</p>
<p dir="RTL">
وبعدها اذهب إلى سطر 37 أو ابحث عن "String address" وقم بكتابة MAC Address الخاص بقطعة البلوتوث.
</p>
<h3 dir="RTL">أردوينو:</h3>
<p dir="RTL">
<p dir="RTL">انسخ ملف IRemote والموجود في مجلد Microcontroller/libraries والصقه في مجلد مكتبات الأردوينو والموجود في الغالب (أو في مجلد أخر إذا قمت بتغيره عند تنصيب الأردوينو) في: "C:\Program Files (x86)\Arduino\libraries"
</p>
<p dir="RTL">ملاحظة: إذا كنت تمتلك ريموت تلفاز من شركة أخرى غير شركة سوني تحتاج إلى فعل الآتي:<p>
<p dir="RTL">قم بتشغيل هذا المثال IRrecvDemo.ino والموجود في هذا الرابط IRremote / examples / IRrecvDemo / IRrecvDemo.ino وتأكد من أن مستقبل أشعة الإنفراريد موصول بـ Pin 11. عند ضغط أي زر في ريموت التلفاز سيظهر الكود الخاص بالزر في شاشة Serial Monitor الخاصة بالأردوينو. سجل الأكواد التي تهمك واكتب قيمها في ملف bluetooth.ino

================
ملاحظة: الكود الحالي مصمم للتعامل مع تلفاز من شركة سوني. 
لكن المكتبة البرمجية المستخدمة مع الأردوينو تدعم سوني وعدة شركات 
أخرى. لذلك في حالة إستخدام شركة أخرى تحتاج أن تخزن قيم الأزرار
المراد إرسالها للتلفاز ويتم ذلك عن طريق إستخدام مستقبل انفراريد مع 
الأردوينو وتسجيل الإشارات المرغوبة.

================
لرؤية المشروع اضغط هنا: 
http://www.youtube.com/watch?v=tlCVr9qbu6o

هذا المشروع يتكون من جوال أندرويد وأردوينو (وحدة تحكم) بحيث يتم التواصل بينهم عن طريق البلوتوث. يقوم جوال
الأندرويد بإرسال أوامر لوحدة التحكم عن طريق البلوتوث للتحكم بالتلفاز مثل رفع الصوت أو تغيير القناة. وبعدها ترسل 
وحدة التحكم الإشارة المناسبة للتفاز عن طريق الأشعة تحت الحمراء.


================
القطع المستخدمة: 

- أردوينو (أنا أستخدمت نفس المايكروكنترولر الموجود في الأردوينو)

http://www.amazon.com/Arduino-UNO-board-DIP-ATmega328P/dp/B006H06TVG/ref=sr_1_1?ie=UTF8&qid=1371296604&sr=8-1&keywords=Arduino+UNO



- جهاز أندرويد 



- دايود أشعة تحت حمراء (Infrared LED):

https://www.sparkfun.com/products/9349


http://www.radioshack.com/product/index.jsp?productId=2062565

RadioShack 
ملاحظة: لم أجرب هذه المستقبلات حيث أن المستقبل الذي أملكه اشترتيه من 

 ولم أجد نفس الموديل في الإنترنت. لكن المفروض أن جميع المستقبلات تعمل بنفس الطريقة



- مقاومة (أي قيمة أكبر من 500 أوم)



- قطعة بلوتوث:

https://www.sparkfun.com/products/10268

ملاحظة: هذه القطعة غالية نوعًا ما. من الممكن إستخدام أي قطعة بلوتوث أخرى
