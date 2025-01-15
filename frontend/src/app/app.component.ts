import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {

  title = 'BilSpot';
  isHomePage: boolean = false;

  public isLogged: boolean = false;
  ShowFooter: boolean = false;

  visible = false;
  constructor(private router: Router, private http: HttpClient) {
    this.router.events.subscribe(() => {
      this.ShowFooter = this.router.url !== '/adopt' &&  this.router.url !== '/gifthouse' &&  this.router.url !== '/free-people';

    })
  }
  ngOnInit(): void {
  }
  closeMenu(){this.visible = false;}
  OpenMenu(){this.visible = true;}

  logout(){
    // this.sharedService.logout();
  }

}
