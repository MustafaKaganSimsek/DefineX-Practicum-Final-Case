import { Component, OnInit } from '@angular/core';
import { Credit } from '../model/credit';
import { CreditService } from '../service/credit.service';

@Component({
  selector: 'find-credit',
  templateUrl: './find-credit.component.html',
  styleUrls: ['./find-credit.component.css']
})
export class FindCreditComponent implements OnInit {

  birthDay?:Date;
  identityNumber?:string;

  credit?:Credit;
  creditLimit?:Number;
  accepted?:boolean

  isError=false;
  errorMessage?:string;

  openModal=false;
  constructor(private service:CreditService) { }

  ngOnInit(): void {
  }
  findCreditByIdentityNumberAndBirthday(birthDay:string,identityNumber:string){
    if(identityNumber==""||birthDay==""){
      this.isError=true
      this.errorMessage="Zorunlu '*' alanlar boş olamaz"
      throw new Error("Zorunlu '*' alanlar boş olamaz");
            
    }
    this.service.findCreditByIdentityNumberAndBirthday(birthDay,identityNumber).subscribe({
      next:(response) =>{
        this.credit=<Credit>response;
        this.creditLimit=this.credit.creditLimit;
        this.accepted=this.credit.accepted;
        this.openModal=true;
        this.isError=false;
  
      },
      error:(err) =>{
        console.log(err);
        this.isError=true;
        this.errorMessage=err.error;
        
      }
    })
  
  }
}
