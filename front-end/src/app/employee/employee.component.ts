import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../authenticate/auth.service';
import { EmpService } from './emp.service';
import { CustomerService } from '../customer/customer.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  depositmsg: string;
  servicemsg: string;
  deletemsg: string;
  viewCustomer: string;
  Accounterror: string;

  constructor(private empService: EmpService,
    private router: Router,
    private authService:AuthService,
    private customerService:CustomerService) { }

  ngOnInit() {
  }

  createAccount(id){
    let val=id.value;
    console.log(id);
    let userIdMatch= /^[A-Za-z0-9]*$/;
    if(!userIdMatch.test(val))
    {
      this.Accounterror="User ID should contain only Alpha-Numeric";
      return;
    }
    this.empService.getDetails(val).subscribe((out)=>{
      this.router.navigate(['/createAccount',val]);
    },error=>{
      this.Accounterror="Invalid CustomerID";
      id.resetForm();
    })
  }

  onDisplay(id){
    let val=id.value;
    let userIdMatch= /^[A-Za-z0-9]*$/;
    if(!userIdMatch.test(val))
    {
      this.viewCustomer="User ID should contain only Alpha-Numeric";
      return;
    }
    this.empService.viewCustomer(val).subscribe((out)=>{
      console.log(out);
      this.router.navigate(['/viewCustomer',val]);
    },error=>{
      this.viewCustomer="Customer UserId DOES NOT EXISTS";
    })

  }

  onDelete(id){
    console.log(id);
    this.empService.onDelete(id.value).subscribe((out)=>{
      console.log(out);
      this.deletemsg="Deleted Successfully";
    },err=>{
      this.deletemsg="Customer UserId DOES NOT EXISTS";
    });
  }

  onDeposit(f){
    let tempId = f.value.accountId;
    let tempAmount = f.value.amount;
    let userIdMatch= /^[1-9][0-9]{9}$/;
    if(!userIdMatch.test(tempId))
    {
      this.depositmsg="Account ID should be 10 digit number..";
      return;
    }
    if(tempAmount<=0)
    {
      this.depositmsg="Deposit amount should be positive..";
      return;
    }
    this.empService.onDeposit(f.value)
    .subscribe((val)=>{
      console.log(val);
      this.depositmsg="Deposit Successfully Done..";
      f.resetForm();
    },error=>{
      this.depositmsg="Invalid Account Id..";
    })
  }

  onServiceCharge(){
    this.empService.onServiceCharge().subscribe((val)=>{
      console.log(val);
      this.servicemsg="Service Charge Deducted";
    },error=>{
      this.servicemsg="Error in Service Charge Deduction";
      console.log(error);
    })
  }

  viewStatement(accountId:any)
  {
    let val=accountId.value;
    this.customerService.getAccount(val).subscribe((out)=>{
      console.log(out);
      this.router.navigate(['/viewStatement',val]);
    },error=>{
      this.deletemsg="Account ID not found";
    })
  }

}
