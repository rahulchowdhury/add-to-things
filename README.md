# Add to Things
![Things logo](https://github.com/rahulchowdhury/add-to-things/blob/master/things-logo.png)
A tiny companion app on Android for adding tasks to Things, since Things is Apple ecosystem only.

# How to build?
You can clone this project and build it with your personalised Things email address.

## Get a Mailgun API key
The first step is to get your own [Mailgun](https://www.mailgun.com/) API key. This will be used for sending emails to your personalised Things email (for adding tasks).

## Create local properties file
The next step is creating a local properties file at the root of this project, called `appconfig.properties` with the following content:

```
FROM_ADDRESS = "your mailgun verified email address here"
TO_ADDRESS = "your personal @things.email address here"
MAILGUN_API_KEY = "your mailgun API key here"
MAILGUN_DOMAIN = "your mailgun domain name here"
```

# Build and enjoy
Just build and run on your device and enjoy adding tasks to your Things inbox.

# License
Copyright © 2019 Rahul Chowdhury

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
