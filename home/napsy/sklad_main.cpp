/****************************************************************************
* C++ sklad
* Copyright (C) 2003, Luka Napotnik
* e-mail: nluka@email.si
*****************************************************************************/
#include <iostream>
using namespace std;

template <class T>
/* Glavni razred za sklad. */
class CStack
{   
    /* Upravljanje z izjemami. */
    class CErrorReport
    {
    public:
        /* Izjema za prazen sklad. */
        class EmptyStack { };
    };
    /* Razred elementa. */
    class node
    {
    public:
        /* Vrednost. */
        T value;
        /* Kazalec na prejšnji element. */
        node *previus;
    };
    
    /* Število elementov v skladu. */
    unsigned int StackSize;
    /* Najvišji element. */
    node *TopNode;
public:
    CStack();
    ~CStack();
    /* Polaganje v sklad. */
    void push(T value);
    /* Klicanje iz sklada. */
    T pop();
    /* Velikost sklada. */
    inline int size() { return(StackSize); }
    /* Preverjanje sklada. */
    bool empty();
    /* Èišèenje sklad*/
    void clear();
};
/////////////////////////////////////////////////////////////////////////////
template <class T> CStack<T>::CStack()
{
    StackSize = 0;
    TopNode = NULL;
}
template <class T> CStack<T>::~CStack()
{
    /* Ob konèanem delu poèistimo sklad. */
    clear();
}
template <class T> void CStack<T>::push(T value)
{
    /* Ustvarimo novi element. */
    node *NewNode = new node;
    /* Dodelimo elementu vrednost. */
    NewNode->value = value;
    /* Èe je to prvi element v skladu, bo kazalec na prejšnji
       element prazen(NULL). */
    if (StackSize == 0)
        NewNode->previus = NULL;
    else
        /* Prejšnji element je trenutno najvišji element. */
        NewNode->previus = TopNode;
    
    /* Sedaj je najvišji element na novo ustvarjen element. */
    TopNode = NewNode;
    /* Poveèamo velikost sklada. */
    StackSize++;
}
template <class T> T CStack<T>::pop() 
{
    T value;
    /* Zaèasna noda. */
    node *temp = TopNode;
    if (StackSize == 0)
        /* Vržemo izjemo, èe je sklad prazen. */
        throw CErrorReport::EmptyStack();
    /* Preberemo vrednost v najvišjem elementu. */
    value = TopNode->value;
    /* Najvišji element postane en element nižje od trenutno najvišjega. */
    TopNode = TopNode->previus;
    
    /* Zbrišemo element v skladu, ki smo ga prebrali. */
    delete temp;
    /* Zmanjšamo število elementov v skladu. */
    StackSize--;
    return(value);
}
template <class T> bool CStack<T>::empty() {
    /* Èe je sklad prazen, vrne 1(true), èe ni pa vrne 0(false). */
    if (StackSize > 0)
        return(0);
    return(1);
}
template <class T> void CStack<T>::clear() {
    /* Zaèasna noda. */
    node *temp = NULL;   
    /* Pobriše vsak element v skladu. */
    for (int i = 1; i <= StackSize; i++) {
        temp = TopNode;
        TopNode->previus = TopNode;
        delete temp;
    }
    StackSize = 0;
    TopNode = NULL;
}
/////////////////////////////////////////////////////////////////////////////
int main(int argc, char *argv[])
{	
    CStack<int> *MojSklad = new CStack<int>;
    int temp = 0;
    for (int x = 0, y = 11; x < 10; x++, y++)
        MojSklad->push(y);
    cout << "Velikost sklada: " << MojSklad->size() << endl;
    while (MojSklad->empty() != 1) {
        try {
            temp = MojSklad->pop();
            cout << "Prebral " << temp << endl;
        }
        catch (CStack<int>::CErrorReport::EmptyStack) {
            cout << "Sklad je prazen!" << endl;
        }
    }
    cin.get();
    
    delete MojSklad;
    return(0);
}

