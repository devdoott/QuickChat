# QuickChat
Has issues regarding generation of authentication key
Libraries used:
    com.android.support:appcompat-v7:23.2.0
    com.android.support:design:23.2.0
    com.android.support:multidex:1.0.0
    telegram-api-1.1.127-shadow.jar
Telegram api link:
https://github.com/ex3ndr/telegram-api.git

The telegram API is unable to generate the authorisation key requred to make RPC calls ,the logCat message is as follows:
MTProto#1001:BadMessage: 48 #6264602863738028576
I think there are some issues on the server end as BadMessage implies that the packets received from the server violate the MT protocol.
The app cannot be further built without making RPC calls . Updates will be given as soon as the issues are resolved.
