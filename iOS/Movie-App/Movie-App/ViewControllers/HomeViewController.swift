//
//  HomeViewController.swift
//  Movie-App
//
//  Created by Ravi Kiran Tummala on 07/02/18.
//  Copyright © 2018 Ravi Kiran Tummala. All rights reserved.
//

import UIKit
import EZLoadingActivity

private let reuseIdentifier = "MovieCell"
private let thumbnailSize = "w185"
private let initialPagesToLoad = 2

class HomeViewController: UIViewController,UICollectionViewDelegate,UICollectionViewDataSource {
    var movieModels = NSMutableArray()
    var totalPages:Int = 0
    var totalResults:Int = 0
    var totalPagesLoaded:Int = 0
    var currentPage:Int = 1
    let moviesPerPage = 20
    var currentSortSelection:Int = 0
    
    @IBOutlet weak var collectionView:UICollectionView!
    @IBAction func sortClick(_ sender: UISegmentedControl) {
        if currentSortSelection != sender.selectedSegmentIndex {
            resetValues()
            switch sender.selectedSegmentIndex{
            case 0:
                loadPopularMoviesInitially()
            case 1:
                loadTopRatedMoviesInitially()
            default:
                loadPopularMoviesInitially()
            }
        }
        currentSortSelection = sender.selectedSegmentIndex
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        EZLoadingActivity.show(LOADING_TEXT, disableUI: true)
        ServiceFactory.getTotalPagesAndResults(completion: { (paginationModel) in
            self.totalPages = paginationModel.totalPages.intValue
            self.totalResults = paginationModel.totalResults.intValue
            EZLoadingActivity.hide(true, animated: true)
        }) { (error) in
            EZLoadingActivity.hide(true, animated: true)
        }
        
        loadPopularMoviesInitially()
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Private Methods
    
    private func loadPopularMovies(){
        EZLoadingActivity.show(LOADING_TEXT, disableUI: true)
        ServiceFactory.getPopularMovieList(forPage: totalPagesLoaded+1, completion: { (movieModelList) in
            self.movieModels.addObjects(from: movieModelList.movieModels)
            self.totalPagesLoaded += 1
            self.collectionView?.reloadData()
            EZLoadingActivity.hide(true, animated: true)
            
        }, failure: { (error) in
            EZLoadingActivity.hide(true, animated: true)
        })
    }
    
    private func loadTopRatedMovies(){
        EZLoadingActivity.show(LOADING_TEXT, disableUI: true)
        ServiceFactory.getTopRatedMovieList(forPage: totalPagesLoaded+1, completion: { (movieModelList) in
            self.movieModels.addObjects(from: movieModelList.movieModels)
            self.totalPagesLoaded += 1
            self.collectionView?.reloadData()
            EZLoadingActivity.hide(true, animated: true)
        }, failure: { (error) in
            EZLoadingActivity.hide(true, animated: true)
        })
    }
    
    private func resetValues(){
        self.totalPagesLoaded = 0
        self.currentPage = 1
        movieModels.removeAllObjects()
        EZLoadingActivity.hide(true, animated: true)
    }
    
    private func loadPopularMoviesInitially(){
        for index in 1...initialPagesToLoad{
            EZLoadingActivity.show(LOADING_TEXT, disableUI: true)
            ServiceFactory.getPopularMovieList(forPage: index, completion: { (movieListModel) in
                self.movieModels.addObjects(from: movieListModel.movieModels)
                self.totalPages = movieListModel.totalPages.intValue
                self.totalResults = movieListModel.totalResults.intValue
                self.totalPagesLoaded += 1
                self.collectionView?.reloadData()
                EZLoadingActivity.hide(true, animated: true)
            }, failure: { (error) in
                EZLoadingActivity.hide(true, animated: true)
            })
        }
    }
    
    private func loadTopRatedMoviesInitially(){
        for index in 1...initialPagesToLoad{
            EZLoadingActivity.show(LOADING_TEXT, disableUI: true)
            ServiceFactory.getTopRatedMovieList(forPage: index, completion: { (movieListModel) in
                self.movieModels.addObjects(from: movieListModel.movieModels)
                self.totalPages = movieListModel.totalPages.intValue
                self.totalResults = movieListModel.totalResults.intValue
                self.totalPagesLoaded += 1
                self.collectionView?.reloadData()
                EZLoadingActivity.hide(true, animated: true)
            }, failure: { (error) in
                EZLoadingActivity.hide(true, animated: true)
            })
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
    }
    */

    // MARK: UICollectionViewDataSource

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return movieModels.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! MovieCell
        let movieModel = self.movieModels.object(at: indexPath.row) as! MovieModel
        // Configure the cell
        Utility.loadSDWebImage(WithSize: thumbnailSize, PosterPath: movieModel.posterPath, ImageView: cell.imageView)
    
        return cell
    }


    // MARK: UICollectionViewDelegate
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        let loadMore = (indexPath.row == movieModels.count-moviesPerPage-1) && (movieModels.count != self.totalResults)
        if loadMore{
            // load more
            print("###### Loading More######")
            currentPage += currentPage
            switch currentSortSelection{
            case 0:
                loadPopularMovies()
            case 1:
                loadTopRatedMovies()
                
            default:
                loadPopularMovies()
            }
        }
    }


    /*
    // Uncomment this method to specify if the specified item should be highlighted during tracking
    override func collectionView(_ collectionView: UICollectionView, shouldHighlightItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment this method to specify if the specified item should be selected
    override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
    override func collectionView(_ collectionView: UICollectionView, shouldShowMenuForItemAt indexPath: IndexPath) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, canPerformAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, performAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) {
    
    }
    */

}
