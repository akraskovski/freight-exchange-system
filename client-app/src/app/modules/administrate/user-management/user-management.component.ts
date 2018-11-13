import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../services/user.service';
import {SearchResponse} from '../../../models/dto/search-response';
import {User} from '../../../models/user';

@Component({
  selector: 'app-users-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  page: number = 0;
  size: number = 10;

  searchResponse: SearchResponse<User>;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.search(this.page, this.size, null).subscribe((response: SearchResponse<User>) => {
      this.searchResponse = response;
      console.log(this.searchResponse);
    });
  }

}
