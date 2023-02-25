import { Component, OnInit } from '@angular/core';
import { log } from 'console';
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
  creditLimit?:Number;
  accepted?:boolean

  isError=false;
  errorMessage?:string;

  openModal=false;

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

    if(name==""||surname==""||identityNumber==""||callNumber==""||birthDay==""||salary==""){
      this.isError=true
      this.errorMessage="Zorunlu '*' alanlar boş olamaz"
      throw new Error("Zorunlu '*' alanlar boş olamaz");
            
    }
      
     
      
      this.service.setCredit(this.customer).subscribe({
        next:(response) =>{
          this.credit=<Credit>response;
          this.creditLimit=this.credit.creditLimit;
          this.accepted=this.credit.accepted;
          this.isError=false
          this.openModal=true
          
  
        },
        error:(err) =>{
          console.log(err);
          this.isError=true;
          this.errorMessage=err.error;
        }
      })

    
    
  }
}
