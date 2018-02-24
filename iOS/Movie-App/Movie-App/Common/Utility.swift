//
//  Utility.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 08/02/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import Foundation

class Utility {
    class func convertToDictionary(fromText text:String) -> [String:Any]?{
        if let data = text.data(using: .utf8) {
            do{
                return try (JSONSerialization.jsonObject(with: data, options: []) as? [String:Any])!
            }catch{
                print(error.localizedDescription)
            }
        }
        return nil
    }
    
    class func convertToArray(fromText text:String) -> [String]?{
        if let data = text.data(using: .utf8) {
            do{
                return try (JSONSerialization.jsonObject(with: data, options: []) as? [String])!
            }catch{
                print(error.localizedDescription)
            }
        }
        return nil
    }
}
