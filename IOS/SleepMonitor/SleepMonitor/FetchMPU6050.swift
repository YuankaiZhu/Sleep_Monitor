//
//  FetchMPU6050.swift
//  SleepMonitor
//
//  Created by Yuankai Zhu on 4/9/21.
//

import Foundation
class FetchMPU6050 {
    func getUsers(completion:@escaping ([MPU6050]) -> ()) {
//        guard let url = URL(string: "https://jsonplaceholder.typicode.com/users") else { return }
        guard let url = URL(string: "http://122.239.216.61/FetchData/FetchData.php") else { return }
//        http://122.239.219.224/FetchData/FetchData.php
        URLSession.shared.dataTask(with: url) { (data, _, _) in
            let users = try! JSONDecoder().decode([MPU6050].self, from: data!)
            DispatchQueue.main.async {
                completion(users)
            }
        }
        .resume()
    }
    
}
