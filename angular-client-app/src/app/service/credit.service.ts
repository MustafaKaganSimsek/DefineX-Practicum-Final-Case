import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import {CustomerFinancialInfo} from "../model/customer-financial-info";

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

  updateCredit(identityNumber:string,customerFinancialInfo: CustomerFinancialInfo){


    return this.http.post(this.url+"/update_with_customer/"+identityNumber,customerFinancialInfo);
  }
}
