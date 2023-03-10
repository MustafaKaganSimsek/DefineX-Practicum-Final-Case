import { Component, OnInit } from '@angular/core';
import { Credit } from '../model/credit';
import { CreditService } from '../service/credit.service';
import {CustomerFinancialInfo} from "../model/customer-financial-info";

@Component({
  selector: 'update-credit',
  templateUrl: './update-credit.component.html',
  styleUrls: ['./update-credit.component.css']
})
export class UpdateCreditComponent implements OnInit {
  identityNumber?:string;
  salary?:string;
  assurance?:string;

  credit?:Credit;

  creditLimit?:Number;
  accepted?:boolean;

  customerFinancialInfo?: CustomerFinancialInfo;

  isError=false;
  errorMessage?:string;

  openModal=false;
  constructor(private service:CreditService) { }

  ngOnInit(): void {
  }

  updateCredit(identityNumber:string,salary:string,assurance:string){
    if(identityNumber==""||salary==""){
      this.isError=true
      this.errorMessage="Zorunlu '*' alanlar boş olamaz"
      throw new Error("Zorunlu '*' alanlar boş olamaz");

    }

    this.customerFinancialInfo={
      salary:salary,
      assurance:assurance
    }
    this.service.updateCredit(identityNumber,this.customerFinancialInfo).subscribe({
      next:(response) =>{
        this.credit=<Credit>response;
        this.creditLimit=this.credit.creditLimit;
        this.accepted=this.credit.accepted;
        this.isError=false;
        this.openModal=true

      },
      error:(err) =>{
        console.log(err);
        this.isError=true;
        this.errorMessage=err.error;      }
    })
  }
}
