import React, { Provider } from 'react';

const createContext = <ContextType>(): readonly [
  () => ContextType,
  Provider<ContextType | undefined>
] => {
  const context = React.createContext<ContextType | undefined>(undefined);
  const useContext = () => {
    const c = React.useContext(context);
    if (!c) throw new Error('useCtx must be inside a Provider with a value');
    return c;
  };
  return [useContext, context.Provider] as const;
};

export default createContext;
