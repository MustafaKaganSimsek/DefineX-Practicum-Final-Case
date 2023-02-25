import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  url="http://localhost:8080/credit"
  constructor(private http:HttpClient) { }

  setCredit(customer:Customer){
    console.log(customer);
    
    return this.http.post(this.url+"/create",customer);
  }

  findCreditByIdentityNumberAndBirthday(birthDay:string,identityNumber:string){
    return this.http.get(this.url+"/customer?identityNumber="+identityNumber+"&birthDay="+birthDay);
  }

  updateCredit(identityNumber:string,salary:string,assurance:string){
    return this.http.post(this.url+"/auto_update/"+identityNumber+"?salary="+salary+"&assurance="+assurance,null)
  }
}
