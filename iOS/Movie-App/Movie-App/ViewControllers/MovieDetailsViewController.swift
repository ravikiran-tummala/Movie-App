//
//  MovieDetailsViewController.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 12/03/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import UIKit
private let imageSize = "w500"
class MovieDetailsViewController: UIViewController {
    
    @IBOutlet weak var movieTitleLabel:UILabel!
    @IBOutlet weak var ratingLabel:UILabel!
    @IBOutlet weak var summaryTextView:UITextView!
    @IBOutlet weak var movieImageView:UIImageView!
    @IBOutlet weak var releaseDateLabel:UILabel!
    
    var movieDetails:MovieModel?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        Utility.loadSDWebImage(WithSize: imageSize, PosterPath: (movieDetails?.posterPath)!, ImageView: self.movieImageView)
        self.movieTitleLabel.text = movieDetails?.originalTitle
        self.navigationItem.title = movieDetails?.title
        self.ratingLabel.text = movieDetails?.voteAverage.stringValue
        self.releaseDateLabel.text = movieDetails?.releaseDate
        self.summaryTextView.text = movieDetails?.overView
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
