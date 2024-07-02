package com.example.pharmaEase.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Generics<T1, T2> {
    private T1 longValue; 
    private T2 intValue; 


}
