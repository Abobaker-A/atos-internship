import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component } from '@angular/core';
import { RegisterUserService } from './../services/register-user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private _RegisterUserService:RegisterUserService

    ) {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required,Validators.minLength(3),Validators.maxLength(20)]],
      lastName: ['', [Validators.required,Validators.minLength(3),Validators.maxLength(20)]],
      birthDate: ['', [Validators.required]],
      gender: ['', [Validators.required,Validators.minLength(4),Validators.maxLength(6)]],
      email: ['', [Validators.email,Validators.required]],
      mobileNumber: ['', [Validators.required]],
      password: ['', [Validators.required,Validators.minLength(3)]],
      address: this.formBuilder.group({
        street: ['', [Validators.required,Validators.minLength(4),Validators.maxLength(40)]],
        city: ['', [Validators.required,Validators.minLength(4),Validators.maxLength(40)]],
        state: ['', [Validators.required,Validators.minLength(4),Validators.maxLength(40)]],
        zipCode: ['', [Validators.required,Validators.minLength(4),Validators.maxLength(40)]]
      })
    });
  }
  showPassword(eventInfo:any){
    const icon = eventInfo.target as HTMLElement;
    const input = document.getElementById("password") as HTMLInputElement;
    if (input) {
      if (input.type === "password") {
        input.type = "text";
        icon.classList.replace("fa-eye-slash" ,"fa-eye");
      } else {
        input.type = "password";
        icon.classList.replace("fa-eye","fa-eye-slash");
      }
    }
  }

  registerUser(){
    if(this.registerForm.invalid){
    console.log(this.registerForm);

      return;
    }
    // this.spinner.show();
    this._RegisterUserService.registerUser(this.registerForm.value).subscribe(()=>{
      console.log("finnnnnnnnnnn");

    // this.spinner.hide();
    });
    // this.spinner.hide();
  }

}
