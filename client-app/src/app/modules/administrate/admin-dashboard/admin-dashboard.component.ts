import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from '../../../models/user';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication.service';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-admin-main',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit, OnDestroy {
  currentUser: User;
  currentUserSubscription: Subscription;
  totalUsersCount: number;
  totalUsersCountSubscription: Subscription;

  constructor(private router: Router, private authService: AuthenticationService, private userService: UserService) {
  }

  ngOnInit() {
    this.currentUserSubscription = this.authService.currentUser.subscribe(user => this.currentUser = user);
    this.totalUsersCountSubscription = this.userService.getTotalCount().subscribe(dto => this.totalUsersCount = dto.totalCount);
  }

  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.currentUserSubscription.unsubscribe();
    this.totalUsersCountSubscription.unsubscribe();
  }

}
