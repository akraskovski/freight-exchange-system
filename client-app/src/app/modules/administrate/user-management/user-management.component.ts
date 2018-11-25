import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../services/user.service';
import {SearchResponse} from '../../../models/dto/search-response';
import {User} from '../../../models/user';
import {PagerService} from '../../../services/pager.service';

@Component({
  selector: 'app-users-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  pager: any = {};
  //TODO: add dynamic combo with possible size values
  pageSize: number = 10;
  searchResponse: SearchResponse<User>;

  constructor(private userService: UserService, private pagerService: PagerService) {
  }

  ngOnInit() {
    this.setPage(1);
  }

  setPage(page: number) {
    this.userService.search(page - 1, this.pageSize, null)
      .subscribe((response: SearchResponse<User>) => {
        this.searchResponse = response;
        this.pager = this.pagerService.getPager(response.totalElements, page, this.pageSize);
      });
  }
}
