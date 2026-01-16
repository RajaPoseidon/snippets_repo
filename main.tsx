import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { SaltProviderNext } from '@salt-ds/core'

// Import Salt theme CSS
import '@salt-ds/theme/index.css'
import '@salt-ds/theme/css/theme-next.css'

import './index.css'
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <SaltProviderNext
      accent="teal"
      actionFont="Open Sans"
      headingFont="Open Sans"
      corner="rounded"
      density="medium"
      mode="dark"
    >
      <App />
    </SaltProviderNext>
  </StrictMode>,
)
