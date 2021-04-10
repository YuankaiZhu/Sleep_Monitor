//
//  ContentView.swift
//  SleepMonitor
//
//  Created by Yuankai Zhu on 4/9/21.
//

import SwiftUI

struct ContentView: View {

    var body: some View {
        HomeView()
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
