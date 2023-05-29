import { Component, OnInit } from '@angular/core';
import { DisplayAllUsersService } from './../services/display-all-users.service';
import { UserData } from './../Modals/user-data';
import { DispalyrolesService } from './../services/dispalyroles.service';
import { RolesDisplay } from './../Modals/RolesDisplay';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-allusers',
  templateUrl: './allusers.component.html',
  styleUrls: ['./allusers.component.css']
})
export class AllusersComponent   implements OnInit {
  constructor(private _DisplayAllUsersService:DisplayAllUsersService,
              private _DispalyrolesService:DispalyrolesService
              ){
  }
  roles:RolesDisplay[]=[];
  users:UserData[]=[];
  page = 1;
  pages:number[]=[];



  DisplayAllUsers(pageNumber:number){
    this.page = pageNumber;
    this._DisplayAllUsersService.DisplayAllUsers(pageNumber).subscribe((data)=>{
      console.log(data);
      this.users = data.content;
      this.pages = new Array(data.totalPages).fill("").map((x,i)=>i+1);
    })
  }
  ngOnInit(): void {
    this.DisplayAllUsers(1);
  }



}
