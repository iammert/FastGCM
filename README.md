# FastGCM
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
    compile 'com.github.iammert:FastGCM:0bf078c8ad'
}
```

Add classpath and repository to root level build.gradle.

```
dependencies {
    classpath 'com.google.gms:google-services:1.3.0-beta1'
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






