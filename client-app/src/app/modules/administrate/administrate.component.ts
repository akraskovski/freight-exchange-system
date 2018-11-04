import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from '../../models/user';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-administrate',
  templateUrl: './administrate.component.html',
  styleUrls: ['./administrate.component.css']
})
export class AdministrateComponent implements OnInit, OnDestroy {
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
