import { Component } from '@angular/core';
import { FormBuilder, FormArray, Validators, FormGroup } from '@angular/forms';
import { AddroleService } from './../services/addrole.service';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-addroles',
  templateUrl: './addroles.component.html',
  styleUrls: ['./addroles.component.css']
})
export class AddrolesComponent {
  roleForm: FormGroup;

  constructor(private formBuilder: FormBuilder , private _AddroleService:AddroleService,
              private _Router:Router,
              private spinner:NgxSpinnerService ,


    ) {
    this.roleForm = this.formBuilder.group({
      name: ['', Validators.required],
      permissions: this.formBuilder.array([])
    });
  }

  get permissionsArray() {
    return this.roleForm.get('permissions') as FormArray;
  }

  addPermission() {
    this.permissionsArray.push(this.formBuilder.control(''));
  }

  removePermission(index: number) {
    this.permissionsArray.removeAt(index);
  }
  createPermissionControl(): FormGroup {
    return this.formBuilder.group({
      permission: ['', Validators.required]
    });
  }
  addPermissionControl(): void {
    const permissions = this.roleForm.get('permissions') as FormArray;
    permissions.push(this.createPermissionControl());
  }
  removePermissionControl(index: number): void {
    const permissions = this.roleForm.get('permissions') as FormArray;
    permissions.removeAt(index);
  }

  addRole(): void {
    this.spinner.show();

    if(this.roleForm.invalid){
      return;
    }
    console.log(this.roleForm.value);
    this._AddroleService.addRole(this.roleForm.value).subscribe(()=>{
      setTimeout(() => {
     this.spinner.hide();

    },500)

      this._Router.navigateByUrl('/roles/allroles')

    })
  }

}
