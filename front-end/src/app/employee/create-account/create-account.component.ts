import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmpService } from '../emp.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  id: string;
  createmsg: string;
  errormsg: string;
  msg:string="alert-success";
  constructor(private route:ActivatedRoute,
    private empService:EmpService) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params)=>{
      this.id=params.get('id');
    })
  }

  createAccount(f){
    let account={
      "customerId":this.id,
      "currentBalance":f.value.Amount,
      "accountType":f.value.accountType,
      "openingDate": new Date().toISOString().slice(0, 10),
      "ownerName": f.value.ownername
    };
    if(account.currentBalance<=500)
    {
      this.errormsg="Account creation balance should be minimum Rs.500";
      this.createmsg="";
       return;
    }
   
    this.empService.createAccount(account,this.id).subscribe((val)=>{
      console.log(val);
      this.createmsg="Created Account";
      this.errormsg="";
    },error=>{
      console.log(error);
      this.errormsg="Error in Account Creation";
      this.createmsg="";
    })
  }

}
