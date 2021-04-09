//
//  ContentView.swift
//  SleepMonitor
//
//  Created by Yuankai Zhu on 4/9/21.
//

import SwiftUI
import SwiftUICharts
struct ContentView: View {
    @State var mpu6050: [MPU6050] = []
    var body: some View {
        VStack{
//            List(mpu6050) { user in
//
//                Text(user.time ?? "")
//                        .font(.headline)
//                Text(user.temp ?? "")
//                        .font(.subheadline)
//            }
//                .onAppear {
//                    FetchMPU6050().getUsers { (mpu6050) in
//                        self.mpu6050 = mpu6050
//                    }
//                }
            let tempsStr:[String?] = mpu6050.map{$0.temp}
            let temps = tempsStr.compactMap{ Double($0 ?? "25") }
            //[8.2,23.4,54,32,12,37,7,23,43]
            let accelXStr:[String?] = mpu6050.map{$0.accel_x}
            let accelX = accelXStr.compactMap{ Double($0 ?? "0") }.map(abs)
            let accelYStr:[String?] = mpu6050.map{$0.accel_y}
            let accelY = accelYStr.compactMap{ Double($0 ?? "0") }.map(abs)
            let accelZStr:[String?] = mpu6050.map{$0.accel_z}
            let accelZ = accelZStr.compactMap{ Double($0 ?? "0") }.map(abs)
            let gyroXStr:[String?] = mpu6050.map{$0.gyro_x}
            let gyroX = gyroXStr.compactMap{ Double($0 ?? "0") }.map(abs)
            let gyroYStr:[String?] = mpu6050.map{$0.gyro_y}
            let gyroY = gyroYStr.compactMap{ Double($0 ?? "0") }.map(abs)
            let gyroZStr:[String?] = mpu6050.map{$0.gyro_z}
            let gyroZ = gyroZStr.compactMap{ Double($0 ?? "0") }.map(abs)

            let motionAccel = zip(zip(accelX,accelY).map(+),accelZ).map(+)
            let motionGyro = zip(zip(gyroX,gyroY).map(+),gyroZ).map(+)
            let motion = zip(motionAccel,motionGyro).map(+)
            
            LineView(data:Array(motion), title: "Sleep Motion").padding()
            LineView(data:Array(temps), title: "Temperature", legend: "Centigrade").padding()
            
            VStack{
                Text("Maximum Temperature: \(temps.max() ?? 0) Centigrade")
                Text("Minimum Temperature: \(temps.min() ?? 0) Centigrade")
                let aveTemp = temps.average
                Text("Average Temperature: \(aveTemp) Centigrade")
                
            }
            
        }
        .onAppear {
                            FetchMPU6050().getUsers { (mpu6050) in
                                self.mpu6050 = mpu6050
                            }
                        }
}
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


extension Array where Element: FloatingPoint {
    
    var sum: Element {
        return reduce(0, +)
    }

    var average: Element {
        guard !isEmpty else {
            return 0
        }
        return sum / Element(count)
    }

}
