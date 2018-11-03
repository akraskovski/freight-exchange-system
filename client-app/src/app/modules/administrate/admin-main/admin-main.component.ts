import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from '../../../models/user';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication.service';

@Component({
  selector: 'app-admin-main',
  templateUrl: './admin-main.component.html',
  styleUrls: ['./admin-main.component.css']
})
export class AdminMainComponent implements OnInit, OnDestroy {
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

}