"# GXandroidSandbox" 


# sandbox module
This sample contains:
- An `External Object` definition.
- An `Android Library` project of a `Genexus Module` that implements an External object.
- An `Genexus Library` definition which declares the `Android Library` implementing the `External Object` in Android.

## Requirements
- Genexus 15 Upgrade 8 or later
- Android SDK
- Android Studio

For more details see [Android Requirements for Genexus 15](http://wiki.genexus.com/commwiki/servlet/wiki?14449).

## Instructions

### Deploy the Genexus Android Module to Genexus
- Set the following environment variables:
    - `GENEXUS_HOME` to your Genexus installation directory.
    - `ANDROID_HOME` to your Android SDK directory.
- Clone this repository.
- Run the command `gradlew uploadArchives` from the project directory.

### Import the Genexus Library definition to Genexus
- Copy the `sandbox\extra\GXsandbox` directory to `%GENEXUS_HOME%\Libraries`.

### Import the External Object definition to Genexus
- Open Genexus.
- Import the `sandbox\extra\sample.xpz` External object definition and sample.
