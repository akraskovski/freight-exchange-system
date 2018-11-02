import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {Router} from '@angular/router';
import {User} from '../../models/user';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  currentUser: User;
  currentUserSubscription: Subscription;

  constructor(private router: Router, private authService: AuthenticationService) {
  }

  ngOnInit() {
    this.currentUserSubscription = this.authService.currentUser.subscribe(user => this.currentUser = user);
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
