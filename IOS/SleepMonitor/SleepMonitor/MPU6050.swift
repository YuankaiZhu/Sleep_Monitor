//
//  MPU6050.swift
//  SleepMonitor
//
//  Created by Yuankai Zhu on 4/9/21.
//

import Foundation
struct MPU6050: Codable, Identifiable {
    let id = UUID()
    let time: String?
    let accel_x:String?
    let accel_y:String?
    let accel_z:String?
    let gyro_x:String?
    let gyro_y:String?
    let gyro_z:String?
    let temp: String?
}
