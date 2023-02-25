import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindCreditComponent } from './find-credit.component';

describe('FindCreditComponent', () => {
  let component: FindCreditComponent;
  let fixture: ComponentFixture<FindCreditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindCreditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindCreditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
