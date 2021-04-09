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
    let temp: String?
}
