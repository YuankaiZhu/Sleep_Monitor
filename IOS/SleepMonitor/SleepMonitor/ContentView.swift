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

            let temps = mpu6050.map{$0.temp}.compactMap{ Double($0 ?? "25") }
            let accelX = mpu6050.map{$0.accel_x}.compactMap{ Double($0 ?? "0") }.map(abs)
            let accelY = mpu6050.map{$0.accel_y}.compactMap{ Double($0 ?? "0") }.map(abs)
            let accelZ = mpu6050.map{$0.accel_z}.compactMap{ Double($0 ?? "0") }.map(abs)
            let gyroX = mpu6050.map{$0.gyro_x}.compactMap{ Double($0 ?? "0") }.map(abs)
            let gyroY = mpu6050.map{$0.gyro_y}.compactMap{ Double($0 ?? "0") }.map(abs)
            let gyroZ = mpu6050.map{$0.gyro_z}.compactMap{ Double($0 ?? "0") }.map(abs)

            let motionAccel = zip(zip(accelX,accelY).map(+),accelZ).map(+)
            let motionGyro = zip(zip(gyroX,gyroY).map(+),gyroZ).map(+)
            let motion = zip(motionAccel,motionGyro).map(+)
            
            LineView(data:Array(motion), title: "Sleep Motion",legend: "m/s^2").padding()
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
