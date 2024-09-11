import { Component, ElementRef, OnInit } from '@angular/core';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { timeout } from 'rxjs/operators';
import { EmpService } from '../emp.service';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {
  createmsg: string;
  errormsg: string;
  userIdMatch:any ;
  panMatch:any;

  constructor(private elementRef:ElementRef,
    private empService: EmpService
    ) { }

  ngOnInit() {
  }

  onSubmit(f){
    let cust={
      "userid":f.value.Id,
      "username":f.value.username,
      "password":f.value.password,
      "dateOfBirth":f.value.dob,
      "pan":f.value.pan,
      "address":f.value.address
    };
    
    this.userIdMatch= /^[A-Za-z0-9]*$/;
    if(!this.userIdMatch.test(cust.userid))
    {
      this.errormsg="User ID should contain only Alpha-Numeric";
      this.createmsg="";
      return;
    }
    if(cust.userid==="")
    {
      this.errormsg="User ID cannot be empty";
      this.createmsg="";
      return;
    }  
    if(cust.username==="")
    {
      this.errormsg="Username cannot be empty";
      this.createmsg="";
      return;
    }
    if(cust.password==="")
    {
      this.errormsg="Password cannot be empty";
      this.createmsg="";
      return;
    }
    this.panMatch=/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/;
    if(!this.panMatch.test(cust.pan))
    {
      this.errormsg="PAN should be of format Ex:CQJPG6145J";
      this.createmsg="";
      return;
    }
    if(cust.address==="")
    {
      this.errormsg="Address cannot be empty";
      this.createmsg="";
      return;
    }
    console.log(cust);
    this.empService.createCustomer(cust).subscribe((val)=>{
      console.log(val);
      this.createmsg="Customer Created Successfully";
      this.errormsg="";
      f.resetForm();
    },error=>{
      console.log(error);
      this.errormsg="Customer No Created... Error Creating..";
      this.createmsg="";
    })
  }
}
