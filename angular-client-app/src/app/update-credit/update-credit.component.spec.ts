import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCreditComponent } from './update-credit.component';

describe('UpdateCreditComponent', () => {
  let component: UpdateCreditComponent;
  let fixture: ComponentFixture<UpdateCreditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCreditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCreditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
