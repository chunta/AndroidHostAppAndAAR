AAR Practice Project

This project serves as a practice for creating Android AAR libraries. It includes:

Integrating various valid resources into the AAR module for use by the host app.
Consolidating crash log functionality within the AAR.
Designing a logging system inside the AAR.


獨立產生 .aar
stay at project root folder, then type 
./gradlew :aarLibrary:assembleDebug --stacktrace
or
./gradlew :aarLibrary:assembleRelease --stacktrace