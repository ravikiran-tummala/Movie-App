//
//  Utility.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 08/02/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import Foundation
import UIKit
import SDWebImage


class Utility {
    class func loadSDWebImage(WithSize size:String,PosterPath posterPath:String,ImageView imageView:UIImageView){
        let urlComponents = NSURLComponents()
        urlComponents.host = POSTER_HOST
        urlComponents.scheme = "http"
        urlComponents.path = POSTER_PATH
        
        var url = urlComponents.url!
        url.appendPathComponent(size)
        url.appendPathComponent("/")
        url.appendPathComponent(posterPath)
        
        
        imageView.sd_setImage(with: url, placeholderImage: UIImage(named: "movie_placeholder_icon.png"), options: []) { (image, error, cacheType, url) in
            print("Error : \(String(describing: error?.localizedDescription))")
        }
        
        
    }
}
