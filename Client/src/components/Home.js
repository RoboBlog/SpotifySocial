import React from 'react';
import * as apiActions from '../actions/apiActions';
import {connect} from 'react-redux';
import Header from '../components/Header'


class HomeComponent extends React.Component {

  render() {
    return (
      <div>
        <Header />

        <section id="main-content">

        <section className="wrapper" >
          <div className="col-lg-3 ds">
            <h3>MY LAST MUSIC</h3>
            <div className="desc">
              <div className="thumb">
                <span className="badge bg-theme"><i class="fa fa-clock-o" /></span>
              </div>
              <div className="details">
                <p><muted>2 Minutes Ago</muted><br/>
                  <a href="#">James Brown</a> subscribed to your newsletter.<br/>
                </p>
              </div>
            </div>
          </div>


          <div className="col-lg-6 main-chart">
            Główny Content
          </div>



          <div className="col-lg-3 ds">
            <h3>NEW RELEASES</h3>

            <div className="desc">
              <div className="thumb">
                <span className="badge bg-theme"><i className="fa fa-clock-o" /></span>
              </div>
              <div className="details">
                <p><muted>2 Minutes Ago</muted><br/>
                  <a href="#">James Brown</a> subscribed to your newsletter.<br/>
                </p>
              </div>
            </div>
            <div className="desc">
              <div className="thumb">
                <span className="badge bg-theme"><i className="fa fa-clock-o" /></span>
              </div>
              <div className="details">
                <p><muted>3 Hours Ago</muted><br/>
                  <a href="#">Diana Kennedy</a> purchased a year subscription.<br/>
                </p>
              </div>
            </div>
            <div className="desc">
              <div className="thumb">
                <span className="badge bg-theme"><i className="fa fa-clock-o" /></span>
              </div>
              <div className="details">
                <p><muted>7 Hours Ago</muted><br/>
                  <a href="#">Brandon Page</a> purchased a year subscription.<br/>
                </p>
              </div>
            </div>

            <div className="desc">
              <div className="thumb">
                <span className="badge bg-theme"><i className="fa fa-clock-o" /></span>
              </div>
              <div className="details">
                <p><muted>11 Hours Ago</muted><br/>
                  <a href="#">Mark Twain</a> commented your post.<br/>
                </p>
              </div>
            </div>

           <div className="desc">
              <div className="thumb">
                <span className="badge bg-theme"><i className="fa fa-clock-o" /></span>
              </div>
              <div className="details">
                <p><muted>18 Hours Ago</muted><br/>
                  <a href="#">Daniel Pratt</a> purchased a wallet in your store.<br/>
                </p>
              </div>
            </div>


            <h3>MUSIC NEWS</h3>

            <div className="desc">
              <div className="thumb">
                <img className="img-circle" src="assets/img/ui-divya.jpg" width="35px" height="35px" align="" />
              </div>
              <div className="details">
                <p><a href="#">DIVYA MANIAN</a><br/>
                  <muted>Available</muted>
                </p>
              </div>
            </div>

           <div className="desc">
              <div className="thumb">
                <img className="img-circle" src="assets/img/ui-sherman.jpg" width="35px" height="35px" align="" />
              </div>
              <div className="details">
                <p><a href="#">DJ SHERMAN</a><br/>
                  <muted>I am Busy</muted>
                </p>
              </div>
            </div>

            <div className="desc">
              <div className="thumb">
                <img className="img-circle" src="assets/img/ui-danro.jpg" width="35px" height="35px" align="" />
              </div>
              <div className="details">
                <p><a href="#">DAN ROGERS</a><br/>
                  <muted>Available</muted>
                </p>
              </div>
            </div>

            <div className="desc">
              <div className="thumb">
                <img className="img-circle" src="assets/img/ui-zac.jpg" width="35px" height="35px" align="" />
              </div>
              <div className="details">
                <p><a href="#">Zac Sniders</a><br/>
                  <muted>Available</muted>
                </p>
              </div>
            </div>

            <div className="desc">
              <div className="thumb">
                <img className="img-circle" src="assets/img/ui-sam.jpg" width="35px" height="35px" align="" />
              </div>
              <div className="details">
                <p><a href="#">Marcel Newman</a><br/>
                  <muted>Available</muted>
                </p>
              </div>
            </div>


          </div>
      </section>
  </section>
      </div>
    //
    // {/*<footer class="site-footer">*/}
    //   {/*<div class="text-center">*/}
    //     {/*Fppter*/}
    //     {/*<a href="index.html#" class="go-top">*/}
    //       {/*<i class="fa fa-angle-up"></i>*/}
    //     {/*</a>*/}
    //   {/*</div>*/}
    // {/*</footer>*/}


    );
  }
}

export default HomeComponent;
