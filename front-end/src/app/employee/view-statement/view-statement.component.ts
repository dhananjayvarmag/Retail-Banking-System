import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from 'src/app/customer/customer.service';
import { EmpService } from '../emp.service';

@Component({
  selector: 'app-view-statement',
  templateUrl: './view-statement.component.html',
  styleUrls: ['./view-statement.component.css']
})
export class ViewStatementComponent implements OnInit {

  transactions: Object;
  msg: string;
  dateMsg:String;
  acId: any;
  constructor(  private customerservice:CustomerService,
    private route:ActivatedRoute,private empService:EmpService) { }
  ngOnInit() {
   this.route.paramMap.subscribe(params => {
     var id = params.get('id');
    console.log(id);  
  });
    let id=this.route.snapshot.params.id;
    console.log(id);
     this.empService.stmt(id).subscribe((val)=>{
      this.transactions = val;
      console.log(this .transactions);    
      this.acId=id;
    },error=>{
      console.log("Error Occured");    
    })
  }

  rangestmt(start,end){
    //this.transactions=null;
  
  let val1=new Date(start.value);
  let val2=new Date(end.value);

  var oneDay = 60 * 60 * 24 * 1000;
  val2 = new Date(val2.getTime() + oneDay);
  //console.log(val1.getTime()," ", val2.getTime()-oneDay);
  if(!(val1.getTime() < val2.getTime()))
  {
    //console.log("reached here...!");
    //console.log(val1.getTime()," ",val2.getTime())
    this.dateMsg="From date should be less than To date."
    return;
  }
  else 
  {
    //console.log("reached here...!");
    this.dateMsg = "";
  }
   this.empService.range(this.acId,start.value,end.value).subscribe((out)=>{
      this.transactions=out;
      console.log(this.transactions);
      //this.msg="Success";
    },error=>{
      this.dateMsg="No transactions";
    })
  }
  
}

