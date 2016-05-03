# FastGCM
[![Join the chat at https://gitter.im/iammert/FastGCM](https://img.shields.io/badge/GITTER-join%20chat-green.svg)](https://gitter.im/iammert/FastGCM)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FastGCM-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2219)

Fast Google Cloud Messaging(GCM) integration library for Android. Includes Topic Messaging.

# Why

We all do same jobs when our application needs GCM. Creating Listeners, services, Broadcast receivers etc. With this
library we can easily integrate GCM to our application. And received message is only thing we care about.

# Library Features

* Receive GCM push messages.
* Subscribe to topic to get topic messages.

# Usage

Add dependency and plugin to project level build.gradle.

```
apply plugin: 'com.google.gms.google-services'

dependencies {
    compile 'com.github.iammert:FastGCM:1.2'
}
```

Add classpath and repository to root level build.gradle.

```
dependencies {
    classpath 'com.google.gms:google-services:2.0.0-alpha6'
}

repositories {
    //..
    maven { url "https://jitpack.io" }
}

```

**IMPORTANT STEP**  Download your google-services.json file and copy to app module. GCM will use this .json file to get senderID and other informations. ([Download it from here](https://developers.google.com/mobile/add?platform=android&cntapi=gcm&cnturl=https:%2F%2Fdevelopers.google.com%2Fcloud-messaging%2Fandroid%2Fclient&cntlbl=Continue%20Adding%20GCM%20Support&%3Fconfigured%3Dtrue))

Register your device to GCM server

```java
//call this onCreate()
GCMManager.getInstance(this).registerListener(this);

//call this onDestroy()
GCMManager.getInstance(this).unRegisterListener();
```

Now you are ready to listen messages.
```java
public class MainActivity extends Activity implements GCMListener{

  @Override
  public void onDeviceRegisted(String token) {}

  @Override
  public void onMessage(String from, Bundle bundle) {}

  @Override
  public void onPlayServiceError() {}
}
```

You can also subscribe and unSubsribe to/from Topics. (You need to call it after onDeviceRegistered())
```java
GCMManager.getInstance(this).subscribeTopic("friendsTopic");
GCMManager.getInstance(this).unSubscribeTopic("friendsTopic");

```

# As a Service
Create your custom service and extend it GCMListenerService. Even if you app is not running, this method is called.
```java
public class CustomGCMService extends GCMListenerService{

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        //Here is called even app is not running.
        //create your notification here.
    }
}
```

Don't forget to add service to your app xml
```xml
<service android:name=".CustomGCMService"
        android:exported="false">
        <intent-filter>
            <action android:name="com.google.android.c2dm.intent.RECEIVE" />
        </intent-filter>
</service>
```
# References

[Developers Google GCM](https://developers.google.com/cloud-messaging/android/client)

[GCM github page](https://github.com/google/gcm)

License
--------


    Copyright 2015 Mert Şimşek.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






