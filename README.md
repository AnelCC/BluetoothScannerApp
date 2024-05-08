# Bluetooth Scanner

🔷 What Is Bluetooth in Android?

Bluetooth in Android refers to the system-level support for short-range wireless communication between devices. It allows Android apps to communicate with other Bluetooth-enabled devices like:
- Wireless headphones or speakers. 
- Smartwatches and fitness trackers
- IoT devices (like robot vacuums, smart tools, etc.)
- Medical sensors 
- Home automation gear (like locks, thermostats)

🧭 Types of Bluetooth in Android
There are two main types:
1. Classic Bluetooth.
   * Used for continuous, high-throughput connections (like audio streaming). 
   * Suitable for devices like headphones or car stereos.
   
2. Bluetooth Low Energy (BLE)
   * Designed for low-power, short-burst communication. 
   * Used for smart devices, sensors, wearables, etc. 
   * Preferred for apps that talk to tools, health devices, or anything battery-powered.

🔧 What Can You Do with Bluetooth in Android?
| Task                     | Classic Bluetooth | BLE |
| ------------------------ | ----------------- | --- |
| Scan for nearby devices  | ✅                 | ✅   |
| Connect to paired device | ✅                 | ✅   |
| Send/receive data        | ✅                 | ✅   |
| Stream audio             | ✅                 | ❌   |
| Battery-efficient        | ❌                 | ✅   |
| Interact with IoT tools  | 🔸                 | ✅   |

📚 Android APIs to Work with Bluetooth
| API                     | Use                                                  |
| ----------------------- | ---------------------------------------------------- |
| `BluetoothAdapter`      | Entry point for all Bluetooth tasks                  |
| `BluetoothDevice`       | Represents a remote device                           |
| `BluetoothSocket`       | For Classic Bluetooth communication                  |
| `BluetoothGatt`         | For BLE communication                                |
| `BluetoothGattCallback` | Receives BLE events (connected, data received, etc.) |
| `BluetoothLeScanner`    | Scans for BLE devices                                |


🔷 BluetoothAdapter Callbacks Overview
| **Component**        | **Callback Method**                              | **Purpose**                                               |
| -------------------- | ------------------------------------------------ | --------------------------------------------------------- |
| `BluetoothAdapter`   | `BluetoothAdapter.LeScanCallback` *(deprecated)* | Callback when BLE device is found during legacy scanning. |
| `BluetoothLeScanner` | `ScanCallback`                                   | Newer way to receive BLE scan results.                    |
| `BluetoothAdapter`   | `BroadcastReceiver` (intent-based)               | Listen for Bluetooth state or discovery events.           |

📦 Why Not Just Use a Callback?
Callbacks like ScanCallback are only for BLE-specific tasks.
But many important changes (Bluetooth state, pairing, etc.) happen at the system level — and callbacks alone can’t observe system-wide changes.

So Android gives you BroadcastReceiver to:
* Know when Bluetooth is turned on/off
* Detect device pairing changes
* Respond to device discovery events

🧩 Example: BLE Use Case
* The app scans for BLE devices.
* Connects to the devices.
* Reads battery level or motor status via GATT.