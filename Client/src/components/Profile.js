import React from 'react';
import Header from '../components/Header'
import {Link} from 'react-router-dom'
import axios from 'axios'
class ProfileComponent extends React.Component {
  constructor() {
    super();
    this.state = {isLoading:false, user:{}}
  }

  componentDidMount() {
    axios.get('http://localhost:8080/api/user/profile',{ headers: {'Authorization': localStorage.getItem('jwtToken')}})
      .then((response) => {
        console.log(response.data);
        this.setState({ isLoading:false, user: response.data })

      })
      .catch((error) => {
        console.log(error);
        window.location.replace('/login')
      });

  }

  render() {



    return (
      //loading page
      <div>
        <Header />
        <section id="main-content">
          <section className="wrapper" style={{backgroundColor: "#f5f5f5"}}>
            <div className="col-lg-6 main-chart" >
              <header>
                <div className="container">
                  <img className="profile-image img-responsive pull-left" src="./assets/img/profile.png" alt="James Lee" />
                  <div className=" pull-left">
                    <h1 className="name">{this.state.user.username}</h1>
                    <h2 className="desc">{this.state.user.email}</h2>
                    <ul className="social list-inline">
                    </ul>
                  </div>
                  {/*<a className="btn btn-cta-primary pull-right" href="http://themes.3rdwavemedia.com/" target="_blank"><i className="fa fa-paper-plane" /> Contact Me</a>*/}
                </div>
              </header>
            </div>
          </section>


          <div className="row padtop">

            <div className="col-md-7 col-sm-12 col-xs-12 " >
              <div className="section-inner">
                <h2>About Me</h2>
                <p>Write a brief intro about yourself. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec.</p>
                <p>It's a good idea to include your personal interests and hobbies as well. Commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec.Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
              </div>
            </div>
            <div className="col-md-5 col-sm-12 col-xs-12" >
              <div className="section-inner">
                <h2>About Me</h2>
                <p>Write a brief innatoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec.</p>
                <p>It's a good idea to include your periam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
              </div>
            </div>
          </div>
        </section>



      </div>

    );
  }
}

export default ProfileComponent;
