import { Component, OnInit } from '@angular/core';
import { DispalyrolesService } from './../services/dispalyroles.service';
import { RolesDisplay } from './../Modals/RolesDisplay';
import { DeleteroleService } from './../services/deleterole.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-allroles',
  templateUrl: './allroles.component.html',
  styleUrls: ['./allroles.component.css']
})
export class AllrolesComponent implements OnInit {
  constructor(private _DispalyrolesService:DispalyrolesService ,
    private spinner:NgxSpinnerService ,
     private _DeleteroleService:DeleteroleService){}
  roles:RolesDisplay[]=[];

  getRoles(){
    this.spinner.show();
    this._DispalyrolesService.getRoles().subscribe((data)=>{
      console.log(data);
      this.roles=data;
      setTimeout(() => {
        this.spinner.hide();

       },500)
    })
  }
  deleteRole(id:string){
    this.spinner.show();
    this._DeleteroleService.deleteRole(id).subscribe(()=>{
      setTimeout(() => {
        this.spinner.hide();

       },500)
      this.getRoles();
    })
    }
  ngOnInit(): void {
    this.getRoles();
  }

}
