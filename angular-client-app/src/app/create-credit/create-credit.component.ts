import { Component, OnInit } from '@angular/core';
import { Credit } from '../model/credit';
import { Customer } from '../model/customer';
import { CreditService } from '../service/credit.service';

@Component({
  selector: 'create-credit',
  templateUrl: './create-credit.component.html',
  styleUrls: ['./create-credit.component.css']
})
export class CreateCreditComponent implements OnInit {
  name?:string;
  surname?:string;
  identityNumber?:string;
  callNumber?:string;
  salary?:string;
  assurance?:string;

  customer?:Customer;

  credit?:Credit;

  constructor(private service:CreditService) { }

  ngOnInit(): void {
  }

  setCredit(name:string,surname:string,identityNumber:string,callNumber:string,birthDay:any,assurance:string,salary:string){
    
    this.customer ={

      name:name,
      surname:surname,
      identityNumber:identityNumber,
      callNumber:callNumber,
      birthDay:birthDay,
      salary:salary,
      assurance:assurance

    }
    console.log(this.customer);
    
    this.service.setCredit(this.customer).subscribe({
      next:(response) =>{
        this.credit=<Credit>response;
        console.log(this.credit);
        alert(this.credit);

      },
      error:(err) =>{
        alert(err.error.message+err.status);
      }
    })
  }
}
