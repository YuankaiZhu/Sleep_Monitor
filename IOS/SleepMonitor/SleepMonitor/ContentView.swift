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
            List(mpu6050) { user in

                Text(user.time ?? "")
                        .font(.headline)
                Text(user.temp ?? "")
                        .font(.subheadline)
            }
                .onAppear {
                    FetchMPU6050().getUsers { (mpu6050) in
                        self.mpu6050 = mpu6050
                    }
                }
            let temps:[String?] = mpu6050.map{$0.temp}
            let doubles = temps.compactMap{ Double($0 ?? "25") }
            //[8.2,23.4,54,32,12,37,7,23,43]
            LineView(data:Array(doubles.prefix(3000)), title: "Line chart", legend: "Full screen").padding()
            // legend is optional, use optional .padding()
        }
}
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
