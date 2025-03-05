# Xheater-Android: BLE Device Scanner & Pairing Android App

## Overview
This Android app is built using Java and allows users to scan for nearby Bluetooth Low Energy (BLE) devices and pair with them. The application leverages Android's Bluetooth API to detect and connect to BLE devices.

## Features
- Scan for nearby BLE devices.
- Display a list of detected devices with their names and MAC addresses.
- Connect and pair with selected BLE devices.
- Maintain a history of previously paired devices.
- Simple and user-friendly interface.

## Prerequisites
Before running the app, ensure that:
- Your Android device supports BLE.
- Bluetooth is enabled on your device.
- Location permissions are granted (required for BLE scanning).
- Target SDK version is set to 31 or above (Android 12+ recommended).

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/dtlaoye/xheater-android-version.git
   cd BLE-Scanner
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and install dependencies.
4. Build and run the app on a physical Android device (BLE requires real hardware).

## Permissions Required
The following permissions must be declared in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.BLUETOOTH_SCAN" android:usesPermissionFlags="neverForLocation" />
<uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
```
For Android 12+ (API level 31 and above), additional permissions need to be requested at runtime.

## How It Works
1. **Scanning for Devices**
   - The app initializes a BLE scanner to detect nearby devices.
   - Results are displayed in a list with device names and MAC addresses.
2. **Connecting to a Device**
   - The user selects a device from the list.
   - The app initiates a connection and attempts pairing.
3. **Pairing & Communication**
   - If the device requires pairing, a system prompt will appear.
   - Upon successful pairing, the app can establish a communication channel.

## Technologies Used
- **Java** (Android Development)
- **Bluetooth Low Energy (BLE) API**
- **Android Jetpack Components** (LiveData, ViewModel, RecyclerView)
- **Gradle** (Build System)

## Troubleshooting
- **Device not found?** Ensure Bluetooth and location services are enabled.
- **Pairing failure?** Check if the device requires a PIN and handle pairing manually.
- **App crashes on start?** Verify that the required permissions are granted.

## Future Enhancements
- Implement a bonded device list for reconnection.
- Improve UI with Material Design components.
- Add support for BLE GATT services.
- Implement real-time data exchange with connected devices.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For any issues or contributions, please open an issue or submit a pull request on GitHub.


