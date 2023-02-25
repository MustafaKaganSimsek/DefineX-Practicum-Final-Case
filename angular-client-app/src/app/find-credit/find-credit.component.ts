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
  constructor(private service:CreditService) { }

  ngOnInit(): void {
  }
  findCreditByIdentityNumberAndBirthday(birthDay:string,identityNumber:string){

    this.service.findCreditByIdentityNumberAndBirthday(birthDay,identityNumber).subscribe({
      next:(response) =>{
        this.credit=<Credit>response;
        console.log(this.credit);
        alert(this.credit);
  
      },
      error:(err) =>{
        alert(err.message);
      }
    })
  
  }
}
