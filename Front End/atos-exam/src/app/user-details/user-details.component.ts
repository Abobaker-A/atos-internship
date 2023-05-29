import { Component } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { DispalyrolesService } from '../services/dispalyroles.service';
import { RolesDisplay } from './../Modals/RolesDisplay';
import { GetUserDetailsService } from './../services/get-user-details.service';
import { ActivatedRoute } from '@angular/router';
import { AssignRolesToUserService } from './../services/assign-roles-to-user.service';
import {  NgxSpinnerService } from 'ngx-spinner';
import { GetNotificationsService } from './../services/get-notifications.service';
import { Notifications } from './../Modals/notifications';
import { MarkReadService } from './../services/mark-read.service';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent {
  roles:RolesDisplay[]=[];
  userData:any;
  userId:string="";
  notifications:Notifications[]=[];
  unReadNotifications:Notifications[]=[];

  constructor(private _DispalyrolesService:DispalyrolesService,
              private _GetUserDetailsService:GetUserDetailsService,
              private _ActivatedRoute:ActivatedRoute,
              private formbuilder:FormBuilder,
              private _AssignRolesToUserService:AssignRolesToUserService,
              private spinner:NgxSpinnerService,
              private _GetNotificationsService:GetNotificationsService,
              private _MarkReadService:MarkReadService,
              ){}

  markRead(id:string){
    this._MarkReadService.markRead(id).subscribe(()=>{
      this.getNotificationsService(this._ActivatedRoute.snapshot.params['id']);
    })
  }


  assignRole = new FormGroup({
    name: new FormControl()
  });

viewQustionDetails(id:string){
  this._GetUserDetailsService.getUserDetails(id).subscribe((data)=>{
    console.log(data);

    this.userData = data;
  })
}
getNotificationsService(id:string){
  this._GetNotificationsService.getNotifications(id).subscribe((data)=>{
    console.log(data);

  this.notifications=data;
  this.unReadNotifications=this.notifications.filter(notification => !notification.read);
  })
}
getFormattedDate(timestamp: string) {
  const date = new Date(timestamp);
  const now = new Date();

  // Calculate the difference between the timestamp and the current time
  const diff = (now.getTime() - date.getTime()) / 1000;

  if (diff < 60) {
    return 'just now';
  } else if (diff < 60 * 60) {
    const mins = Math.floor(diff / 60);
    return `${mins} min${mins > 1 ? 's' : ''} ago`;
  } else if (diff < 60 * 60 * 24) {
    return `yesterday at ${date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}`;
  } else {
    return date.toLocaleDateString() + ' at ' + date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  }
}


  ngOnInit(): void {
    this.userId=this._ActivatedRoute.snapshot.params['id'];
    this.viewQustionDetails(this._ActivatedRoute.snapshot.params['id']);
    this.getNotificationsService(this._ActivatedRoute.snapshot.params['id']);
    this.getRoles();

  }
  getRoles(){
    this._DispalyrolesService.getRoles().subscribe((data)=>{
      console.log(data);
      this.roles=data;
    })
  }

  toggleRole(roleId: string) {
    const roles = this.rolesForm.value.roles as string[];
    const index = roles.indexOf(roleId);
    if (index === -1) {
      roles.push(roleId);
    } else {
      roles.splice(index, 1);
    }
    this.rolesForm.patchValue({ roles });
  }
  submitRoles() {
    this.spinner.show();
    const id = this.userId;
    const roles = this.rolesForm.value.roles as string[];
    this._AssignRolesToUserService.assignRolesToUser(id,roles).subscribe(()=>{
      this.viewQustionDetails(this._ActivatedRoute.snapshot.params['id']);
    })
    setTimeout(() => {
      this.spinner.hide();
    }, 250);

  }
  rolesForm:FormGroup = this.formbuilder.group({
    roles: [[]]
  });



}
