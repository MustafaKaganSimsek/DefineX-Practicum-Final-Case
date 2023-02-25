import { Component, OnInit } from '@angular/core';
import { Credit } from '../model/credit';
import { CreditService } from '../service/credit.service';

@Component({
  selector: 'update-credit',
  templateUrl: './update-credit.component.html',
  styleUrls: ['./update-credit.component.css']
})
export class UpdateCreditComponent implements OnInit {
  identityNumber?:string;
  salary?:string;
  assurance?:string;

  credit?:Credit
  constructor(private service:CreditService) { }

  ngOnInit(): void {
  }

  updateCredit(identityNumber:string,salary:string,assurance:string){
    this.service.updateCredit(identityNumber,salary,assurance).subscribe({
      next:(response) =>{
        this.credit=<Credit>response;
        console.log(this.credit.creditLimit);
        alert(this.credit);

      },
      error:(err) =>{
        alert(err.error.message+err.status);
      }
    })
  }
}
